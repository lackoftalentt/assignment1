package entities;

public abstract class SchoolPerson {
    protected final int id;
    protected final int schoolId;
    protected String name;

    protected SchoolPerson(int id, int schoolId, String name) {
        this.id = id;
        this.schoolId = schoolId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getRole();
}
