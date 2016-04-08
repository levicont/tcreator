package com.lvg.tcreator.services;

import java.util.List;

public interface GenericService<T> {
	
	public void save(T record);
	public T get(long id);
	public List<T> getAll();
	public void update(T record);
	public void delete(T record);

}
