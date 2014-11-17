package com.forerunner.web.i18n;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class FRMessageSource extends ReloadableResourceBundleMessageSource {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Value("fr.web.massage.table")
	private String massageTable;

	private Map<String, String> cachedMessages = new HashMap<String, String>();

	public FRMessageSource() {
		setFallbackToSystemLocale(false);
	}

	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		String message = null;

		if (cachedMessages.containsKey(code)) {
			message = cachedMessages.get(code);
		} else {
			message = loadMessage(code);
			cachedMessages.put(code, message);
		}

		if (message != null) {
			return message;
		} else {
			return super.resolveCodeWithoutArguments(code, locale);
		}
	}

	protected MessageFormat resolveCode(String code, Locale locale) {
		String message = null;

		if (cachedMessages.containsKey(code)) {
			message = cachedMessages.get(code);
		} else {
			message = loadMessage(code);
			cachedMessages.put(code, message);
		}

		if (message != null) {
			return new MessageFormat(message, locale);
		} else {
			return super.resolveCode(code, locale);
		}
	}

	private String loadMessage(String code) {
		String sqlStatement = "select * from " + massageTable + " where messageCode = ?";

		List<String> results = jdbcTemplate.query(sqlStatement, new Object[] { code }, new BafMessageCodeMapper());

		if (results.isEmpty())
			return null;
		else
			return results.get(0);
	}

	private static final class BafMessageCodeMapper implements RowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("message");
		}
	}

	public void clearMessageCache(String code) {
		cachedMessages.remove(code);
	}

	public void clearBafMessageCache() {
		cachedMessages.clear();

	}
}