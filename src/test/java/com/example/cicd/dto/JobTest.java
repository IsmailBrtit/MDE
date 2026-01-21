package com.example.cicd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    private Job job;

    @BeforeEach
    void setUp() {
        job = new Job();
    }

    @Test
    void testSetAndGetName() {
        String jobName = "build-job";
        job.setName(jobName);
        assertEquals(jobName, job.getName());
    }

    @Test
    void testSetRunsOn() {
        String customRunner = "windows-latest";
        job.setRunsOn(customRunner);
        assertEquals(customRunner, job.getRunsOn());
    }

    @Test
    void testAddEnvVariable() {
        EnvVariable env = new EnvVariable("NODE_ENV", "production");
        job.addEnvVariable(env);
        
        assertNotNull(job.getEnvVariables());
        assertTrue(job.getEnvVariables().contains(env));
        assertEquals(1, job.getEnvVariables().size());
    }

    @Test
    void testAddMultipleEnvVariables() {
        EnvVariable env1 = new EnvVariable("KEY1", "value1");
        EnvVariable env2 = new EnvVariable("KEY2", "value2");
        
        job.addEnvVariable(env1);
        job.addEnvVariable(env2);
        
        assertEquals(2, job.getEnvVariables().size());
    }

    @Test
    void testAddNeed() {
        String dependency = "test-job";
        job.addNeed(dependency);
        
        assertNotNull(job.getNeeds());
        assertTrue(job.getNeeds().contains(dependency));
        assertEquals(1, job.getNeeds().size());
    }

    @Test
    void testAddMultipleNeeds() {
        job.addNeed("job1");
        job.addNeed("job2");
        job.addNeed("job3");
        
        assertEquals(3, job.getNeeds().size());
    }

    @Test
    void testJobWithCompleteConfiguration() {
        job.setName("deploy");
        job.setRunsOn("ubuntu-22.04");
        job.addEnvVariable(new EnvVariable("ENV", "prod"));
        job.addNeed("build");
        
        assertEquals("deploy", job.getName());
        assertEquals("ubuntu-22.04", job.getRunsOn());
        assertEquals(1, job.getEnvVariables().size());
        assertEquals(1, job.getNeeds().size());
    }
}