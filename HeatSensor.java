import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeatSensor {
    private List<Member> observerList= new ArrayList<Member>();
    public void addMember(Member member){
        observerList.add(member);
    }
    public void deleteMember(Member member){
        observerList.remove(member);
    }
    private void notifyMember(int heat){
        for(int i=0;i<observerList.size();i++){
            observerList.get(i).alarm(heat);
        }
    }
    public int dedectTemperature(){
        Random random= new Random();
        int heat=random.nextInt(60)-10;
        if(heat<0||heat>40) notifyMember(heat);
        return heat;
    }
}
