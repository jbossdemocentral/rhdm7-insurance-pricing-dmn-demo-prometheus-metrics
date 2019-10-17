package com.redhat.demos.dm.prometheus;

import org.jbpm.executor.AsynchronousJobListener;
import org.jbpm.services.api.DeploymentEventListener;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.dmn.api.core.event.DMNRuntimeEventListener;
import org.kie.server.services.api.KieContainerInstance;
import org.kie.server.services.prometheus.PrometheusMetricsProvider;
import org.optaplanner.core.impl.phase.event.PhaseLifecycleListener;

/**
 * InsurancePricingPrometheusMetricsProvider
 */
public class InsurancePricingPrometheusMetricsProvider implements PrometheusMetricsProvider {

    public AgendaEventListener createAgendaEventListener(String arg0, KieContainerInstance arg1) {
        return null;
    }

    public AsynchronousJobListener createAsynchronousJobListener() {
        return null;
    }

    public DMNRuntimeEventListener createDMNRuntimeEventListener(KieContainerInstance kci) {
        return new InsurancePricingDMNListener();
    }

    public DeploymentEventListener createDeploymentEventListener() {
        return null;
    }

    public PhaseLifecycleListener createPhaseLifecycleListener(String arg0) {
        return null;
    } 
}