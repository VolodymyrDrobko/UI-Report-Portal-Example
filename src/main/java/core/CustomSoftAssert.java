package core;

import org.apache.logging.log4j.Level;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Map;

public class CustomSoftAssert extends Assertion {
    private final Map<AssertionError, IAssert<?>> errorsMap = Maps.newLinkedHashMap();
    String assertResultMessage = "";

    @Override
    public void doAssert(IAssert<?> assertionBody) {
        onBeforeAssert(assertionBody);
        try {
            assertionBody.doAssert();
            onAssertionSuccess();
        } catch (AssertionError assertionError) {
            onAssertionFail(assertionBody, assertionError);
        }
    }

    @Override
    public void onBeforeAssert(IAssert assertionBody) {
        assertResultMessage = "Verification of " + assertionBody.getMessage();
    }

    private void onAssertionSuccess() {
        LoggerManager.getLogger().log(Level.INFO, assertResultMessage + " - PASSED SUCCESSFUL");
    }

    private void onAssertionFail(IAssert assertionBody, AssertionError assertionError) {
        errorsMap.put(assertionError, assertionBody);
        LoggerManager.getLogger().log(Level.ERROR, assertResultMessage + " >>>>> FAILED");
    }

    public void assertAll() {
        if (!errorsMap.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            for (Map.Entry<AssertionError, IAssert<?>> assertionErrorBody : errorsMap.entrySet()) {
                AssertionError errorBody = assertionErrorBody.getKey();
                IAssert assertionBody = assertionErrorBody.getValue();
                sb.append("\n\t");
                sb.append("Verification step - " + assertionBody.getMessage() + "\n");
                sb.append("Actual - " + assertionBody.getActual() + "\n");
                sb.append("Expected - " + assertionBody.getExpected() + "\n");
                sb.append(errorBody.getStackTrace()[6] + "\n");
            }
            errorsMap.clear();
            throw new AssertionError(sb.toString());
        }
    }
}
