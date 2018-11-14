package model;


import anotations.Column;
import anotations.Id;

public abstract class _IDEntity implements _iIDEntity {

    @Id
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
