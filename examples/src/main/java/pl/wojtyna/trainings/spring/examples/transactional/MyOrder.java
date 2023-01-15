package pl.wojtyna.trainings.spring.examples.transactional;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyOrder {

    @Id
    //    @GeneratedValue(generator = "uuid")
    //    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    boolean paid = false;

    public MyOrder() {}

    public void paid() {
        this.paid = true;
    }

    public String getId() {return this.id;}

    public void setId(String id) {this.id = id;}

    public boolean isPaid() {return this.paid;}

    public void setPaid(boolean paid) {this.paid = paid;}

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MyOrder)) {
            return false;
        }
        final MyOrder other = (MyOrder) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
            return false;
        }
        if (this.isPaid() != other.isPaid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        result = result * PRIME + (this.isPaid() ? 79 : 97);
        return result;
    }

    @Override
    public String toString() {return "MyOrder(id=" + this.getId() + ", paid=" + this.isPaid() + ")";}

    protected boolean canEqual(final Object other) {return other instanceof MyOrder;}
}
