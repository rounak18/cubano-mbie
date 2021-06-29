package nz.govt.mbie.framework;

import org.concordion.api.ConcordionResources;
import org.concordion.api.FailFast;
import org.concordion.api.extension.Extensions;
import org.concordion.cubano.framework.ConcordionFixture;
import org.concordion.ext.TimestampFormatterExtension;
import org.concordion.ext.runtotals.RunTotalsExtension;
import org.concordion.ext.statusinfo.StatusInfoExtension;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

import nz.govt.mbie.AppConfig;

/**
 * A base class for extension by fixtures that contain assertions.
 *
 * @see CubanoMbieIndex for fixtures that don't contain assertions
 * @see CubanoMbieBrowserFixture for fixtures that invoke a browser
 */
@ConcordionResources("/customConcordion.css")
@Extensions({ TimestampFormatterExtension.class, RunTotalsExtension.class, StatusInfoExtension.class })
@FailFast
public abstract class CubanoMbieFixture extends ConcordionFixture {
    protected final ReportLogger reportLogger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

    static {
        AppConfig.getInstance().logSettings();

    }

    /** Override the default logger. **/
    public CubanoMbieFixture() {
        super.withFixtureListener(new CubanoMbieFixtureLogger());
    }
}
