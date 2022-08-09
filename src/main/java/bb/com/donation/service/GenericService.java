package bb.com.donation.service;


import java.util.List;

public interface GenericService<T, T1, T2> {
    T save(T2 t);
    T getById(T1 t1);
    List<T> getAll();
    void delete(T1 t1);
}
