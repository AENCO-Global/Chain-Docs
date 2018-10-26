package AEN.guides.examples.account;

import io.AEN.sdk.infrastructure.AccountHttp;
import io.AEN.sdk.model.account.Address;
import io.AEN.sdk.model.account.MultisigAccountInfo;
import io.AEN.sdk.model.account.PublicAccount;
import io.AEN.sdk.model.blockchain.NetworkType;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

public class GettingMultisigAccountInformation {


    @Test
    void gettingMultisigAccountInformation() throws ExecutionException, InterruptedException, MalformedURLException {
        final AccountHttp accountHttp = new AccountHttp("http://localhost:3000");

        // Replace with address
        final String addressRaw = "SB2RPH-EMTFMB-KELX2Y-Q3MZTD-RV7DQG-UZEADV-CYKC";

        final Address address = Address.createFromRawAddress(addressRaw);

        final MultisigAccountInfo multisigAccountInfo = accountHttp.getMultisigAccountInfo(address).toFuture().get();

        System.out.println(multisigAccountInfo);
    }
}
