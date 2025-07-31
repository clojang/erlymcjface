package io.github.clojang.mcjface.core.node;

public record NodeConfig(String name, String cookie) {

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String name;
    private String cookie;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder cookie(String cookie) {
      this.cookie = cookie;
      return this;
    }

    public NodeConfig build() {
      return new NodeConfig(name, cookie);
    }
  }
}
