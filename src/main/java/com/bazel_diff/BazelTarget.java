package com.bazel_diff;

import com.google.devtools.build.lib.query2.proto.proto2api.Build;

interface BazelTarget {
    boolean hasRule();
    BazelRule getRule();
    boolean hasSourceFile();
    String getSourceFileName();
    boolean hasGeneratedFile();
    String getGeneratedFileName();
    String getGeneratingRuleName();
}

class BazelTargetImpl implements BazelTarget {
    private Build.Target target;

    public BazelTargetImpl(Build.Target target) {
        this.target = target;
    }

    @Override
    public boolean hasRule() {
        return target.hasRule();
    }

    @Override
    public BazelRule getRule() {
        if (this.hasRule()) {
            return new BazelRuleImpl(target.getRule());
        }
        return null;
    }

    @Override
    public boolean hasSourceFile() {
        return target.hasSourceFile();
    }

    @Override
    public String getSourceFileName() {
        if (this.hasSourceFile()) {
            return this.target.getSourceFile().getName();
        }
        return null;
    }

    @Override
    public boolean hasGeneratedFile() {
        return target.hasGeneratedFile();
    }

    @Override
    public String getGeneratedFileName() {
        if (this.hasGeneratedFile()) {
            return this.target.getGeneratedFile().getName();
        }
        return null;
    }

    @Override
    public String getGeneratingRuleName() {
        if (this.hasGeneratedFile()) {
            return this.target.getGeneratedFile().getGeneratingRule();
        }
        return null;
    }
}

