package com.forerunner.core.notification;

import com.forerunner.persistent.entity.FRDictionary;

public interface NotificationStatus {
	FRDictionary RECEIVED = new FRDictionary("RECEIVED", 0);
	FRDictionary NOT_RECEIVED = new FRDictionary("NOT_RECEIVED", 1);
	FRDictionary CANCEL = new FRDictionary("CANCEL", -1);
}