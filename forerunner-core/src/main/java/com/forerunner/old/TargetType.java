package com.forerunner.old;

import com.forerunner.core.persistent.Dictionary;

/**
 * 目标类型
 * @author GQ
 */
public interface TargetType {
	Dictionary<String, Integer> PERSONAL = Dictionary.def("PERSONAL", 1);
	Dictionary<String, Integer> TEAM = Dictionary.def("TEAM", 2);
	Dictionary<String, Integer> TEMPORARY = Dictionary.def("TEMPORARY", 3);
}