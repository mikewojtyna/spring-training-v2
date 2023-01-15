package pl.wojtyna.trainings.spring.examples.transactional;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;

    public Payment() {}

    public String getId() {return this.id;}

    public void setId(String id) {this.id = id;}

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        final Payment other = (Payment) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
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
        return result;
    }

    @Override
    public String toString() {return "Payment(id=" + this.getId() + ")";}

    protected boolean canEqual(final Object other) {return other instanceof Payment;}
}
