public class Member {
    private String name;

    Member(String name){
        this.name=name;
    };

    public void alarm(int heat){
        System.out.println(name+" sıcaklık "+heat+" düzeyine ulaştı!!!");
    };
}
