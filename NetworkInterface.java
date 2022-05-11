import Database.DatabaseFactory;
import Database.IDatabaseRepository;

import java.io.IOException;
import java.util.Scanner;

public class NetworkInterface {
    private enum status{
        close,
        open,
        Opening_Test,
        Waiting,
        Dedecting,
        OutOfService,
        InProgress;
    }
    private status currentStatu=status.close;
    public void start(){
        IDatabaseRepository database;
        database= DatabaseFactory.databaseFactory("postgresql");
        database.openConnection();

        boolean check=false;

        Scanner scanner =new Scanner(System.in);
        for(int i=0;i<3;i++){//3 kere hatalı mesaj deneme hakkı
            showMessage("Kullanıcı Adı:");
            String username = scanner.nextLine();
            showMessage("Şifre:");
            String password= scanner.nextLine();
            if(database.verifyUser(username,password)){//Kullanıcı adı ve şifre doğrulama
                showMessage("Sisteme Hoşgeldiniz:\n");
                check=true;
                break;
            }
        }
        if(!check){
            showMessage("Sisteme 3 kere hatalı şifre girdiniz program kapatılıyor!!!");
            System.exit(0);
        }
        database.closeConnection();

        Actuator actuator=new Actuator();
        HeatSensor heatSensor= new HeatSensor();

        Member m1= new Member("Ali");
        Member m2= new Member("Ahmet");
        Member m3= new Member("Veli");
        Member m4= new Member("Ayşe");
        heatSensor.addMember(m1);
        heatSensor.addMember(m2);
        heatSensor.addMember(m3);
        heatSensor.addMember(m4);

        currentStatu=status.Opening_Test;
        showMessage("Açılış Testi Yapılıyor...\n");
        currentStatu=status.Waiting;
        do{//Ekran döngüsü
            showMessage("İşlem Seçiniz:\n"+
                    "1-Soğutuyucu Aç\n" +
                    "2-Soğutucuyu Kapat\n" +
                    "3-Sıcaklığı Görüntüle\n" +
                    "4-Çıkış\n");
            int chose=Integer.valueOf(scanner.nextLine());
            switch (chose){//Seçim
                case 1:
                    showMessage(actuator.openCooler(currentStatu.toString()));
                    currentStatu=status.InProgress;
                    break;
                case 2:
                    showMessage(actuator.closeCooler(currentStatu.toString()));
                    currentStatu=status.Waiting;
                    break;
                case 3:
                    showMessage("Sıcaklık değeri:"+heatSensor.dedectTemperature()+"\n");
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }while (currentStatu!=status.OutOfService);
    }
    public void showMessage(String message){
        System.out.print("Durum:"+currentStatu+"\n"+message);
    }

}
