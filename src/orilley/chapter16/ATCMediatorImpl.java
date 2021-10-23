package orilley.chapter16;

import java.util.ArrayList;
import java.util.List;

// concrete mediator
public class ATCMediatorImpl implements ATCMediator {
    private List<AirCraft> airCraftList;

    public ATCMediatorImpl() {
        this.airCraftList = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, AirCraft airCraft) {
        for (AirCraft craft : airCraftList) {
            // 자기가 자기 자신에게 메시지를 보내지 않음
            if (craft != airCraft) {
                craft.receive(message);
            }
        }
    }

    @Override
    public void addAircraft(AirCraft airCraft) {
        airCraftList.add(airCraft);
    }
}
