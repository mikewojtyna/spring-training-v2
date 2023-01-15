package pl.wojtyna.trainings.spring.examples.transactional;

public interface PersistableRepo {

    MyOrder persist(MyOrder order);
}
