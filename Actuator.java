public class Actuator {
    public String openCooler(String statu){
        if(statu=="InProgress") return "Soğutucu zaten açık!!!";
        return "Soğutucu Açıldı.";
    }
    public String closeCooler(String statu){
        if(statu!="InProgress") return "Soğutucu zaten kapalı!!!";
        return "Soğutucu Kapatıldı.";
    }
}
