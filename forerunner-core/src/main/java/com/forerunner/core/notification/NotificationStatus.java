package com.forerunner.core.notification;

import com.forerunner.persistent.entity.Dictionary;

public interface NotificationStatus {
	Dictionary<String, Integer> RECEIVED = Dictionary.def("RECEIVED", 0);
	Dictionary<String, Integer> NOT_RECEIVED = Dictionary.def("NOT_RECEIVED", 1);
	Dictionary<String, Integer> CANCEL = Dictionary.def("CANCEL", -1);
}