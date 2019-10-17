package com.redhat.demos.dm.prometheus;

import java.math.BigDecimal;

import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.kie.dmn.api.core.event.AfterEvaluateDecisionEvent;
import org.kie.dmn.api.core.event.DMNRuntimeEventListener;

import io.prometheus.client.Histogram;

/**
 * InsurancePricingDMNListener
 */
public class InsurancePricingDMNListener implements DMNRuntimeEventListener {

    private static final Histogram insurancePricing = Histogram.build()
                                                            .name("insurance_pricing")
                                                            .help("Insurance Pricing")
                                                            .labelNames("decision_namespace", "decision_name")
                                                            .buckets(1000, 2000, 3000)
                                                            .register();

    
    public InsurancePricingDMNListener() {
    }

    @Override
    public void afterEvaluateDecision(AfterEvaluateDecisionEvent event) {
        DecisionNode decisionNode = event.getDecision();

        String decisionNodeName = decisionNode.getName();
        DMNDecisionResult result = event.getResult().getDecisionResultByName(decisionNodeName);
        
        switch (decisionNodeName) {
            case "Insurance Total Price":
                double resultAsDouble = 0.0;
                Object resultAsObject = result.getResult();
                //We only store if we find and Integer.
                if (resultAsObject instanceof BigDecimal) {
                    resultAsDouble = ((BigDecimal) resultAsObject).doubleValue();
                    insurancePricing.labels(decisionNode.getModelName(), decisionNode.getModelNamespace()).observe(resultAsDouble);
                }                
                break;
            default:
                //Not the decision we want to monitor. Discarding.
                break;
        }
    }
}