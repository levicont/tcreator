package com.lvg.tcreator.services;

public interface GenericService<T> {
	
	public void save(T record);
	public T get(int id);
	public void update(T record);
	public void delete(T record);

}
