package com.pack.bank.service;

import java.util.List;

public interface ManageData<T,A> {
	int add(T data);
	int delete(A id);
	List<T> viewAll();
}
