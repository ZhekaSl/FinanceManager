package financeManager.saveLoad;

import financeManager.settings.Settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.Set;

public class SaveLoad {

    public static void load(SaveData sd) {
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Wrapper wrapper = (Wrapper) unmarshaller.unmarshal(Settings.getFileSave());
            sd.setAccounts(wrapper.getAccounts());
            sd.setArticles(wrapper.getArticles());
            sd.setTransactions(wrapper.getTransactions());
            sd.setTransfers(wrapper.getTransfers());
            sd.setCurrencies(wrapper.getCurrencies());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    public static void save(SaveData sd) {
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Marshaller marshaller = context.createMarshaller();
            Wrapper wrapper = new Wrapper();
            wrapper.setAccounts(sd.getAccounts());
            wrapper.setArticles(sd.getArticles());
            wrapper.setTransfers(sd.getTransfers());
            wrapper.setTransactions(sd.getTransactions());
            wrapper.setCurrencies(sd.getCurrencies());
            marshaller.marshal(wrapper, Settings.getFileSave());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
