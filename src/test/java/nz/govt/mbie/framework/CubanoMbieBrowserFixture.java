package nz.govt.mbie.framework;

import java.io.Closeable;

import org.concordion.api.ConcordionResources;
import org.concordion.api.FailFast;
import org.concordion.api.extension.Extension;
import org.concordion.api.extension.Extensions;
import org.concordion.cubano.driver.concordion.ExceptionHtmlCaptureExtension;
import org.concordion.cubano.framework.ConcordionBrowserFixture;
import org.concordion.cubano.framework.resource.CloseListener;
import org.concordion.cubano.framework.resource.ResourceScope;
import org.concordion.ext.TimestampFormatterExtension;
import org.concordion.ext.runtotals.RunTotalsExtension;
import org.concordion.ext.statusinfo.StatusInfoExtension;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

/**
 * A base class for extension by fixtures that invoke a browser, and may also use HttpEasy.
 *
 * Customises the test specification and provides some helper methods so the tests can access the storyboard, browser, etc.
 *
 * @see CubanoMbieIndex for fixtures that don't contain assertions
 * @see CubanoMbieFixture for fixtures that don't invoke a browser
 */
@ConcordionResources("/customConcordion.css")
@Extensions({ TimestampFormatterExtension.class, RunTotalsExtension.class, StatusInfoExtension.class })
@FailFast
public abstract class CubanoMbieBrowserFixture extends ConcordionBrowserFixture {
    protected final ReportLogger reportLogger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

    @Extension
    private final ExceptionHtmlCaptureExtension htmlCapture = new ExceptionHtmlCaptureExtension(getStoryboard(), getBrowser());

    static {
    }

    /** Override the default fixture logger. **/
    public CubanoMbieBrowserFixture() {
        super.withFixtureListener(new CubanoMbieFixtureLogger());
    }

    @Override
    public void registerCloseableResource(Closeable resource, ResourceScope scope) {
        CloseListener listener = new CloseListener() {

            @Override
            public void beforeClosing(Closeable resource) {
                // Prevent any further cards being added to the storyboard
                getStoryboard().setAcceptCards(false);
                reportLogger.step("Clean up data for " + resource);
            }

            @Override
            public void afterClosing(Closeable resource) {
                getStoryboard().setAcceptCards(true);
            }
        };
        super.registerCloseableResource(resource, scope, listener);
    }
}
