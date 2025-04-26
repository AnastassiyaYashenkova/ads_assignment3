public class MyTest {
    private int id;
    private String name;

    public MyTest(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        MyTest that = (MyTest) object;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode(){
        int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyTest {id = " + id + ", name = " + name + "}";
    }
}
