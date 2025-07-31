# erlymcjface

[![Build Status][gh-actions-badge]][gh-actions]

[![Project Logo][logo]][logo-large]

*Erly McJFace is an experimental replacement for the Erlang JInterface Java library*

## WIP

The current Erlang/OTP JInterface implementation, while functional, fails to provide the robustness and elegance of
Erlang/OTP. The proposed redesign addresses fundamental issues while providing a modern, type-safe API that modern Java 
developers will find sensible and appropriate. By implementing proper supervision, process isolation, and even some OTP
behaviors, we can create a library that can truly bridge Java and Erlang/OTP systems.

The key improvements include:

1. True process isolation with supervision
2. Type-safe, immutable term representation
3. Non-blocking, reactive programming model
4. Proper resource management
5. Comprehensive error handling
6. Full OTP behavior support

This redesign will result in a library that is not only more robust and maintainable but also more aligned with both
Erlang/OTP principles and modern Java best practices.

[//]: ---Named-Links---

[logo]: resources/images/logo.png
[logo-large]: resources/images/logo-large.png
[gh-actions-badge]: https://github.com/clojang/erlymcjface/workflows/CI/badge.svg
[gh-actions]: https://github.com/clojang/erlymcjface/actions?query=workflow%3ACI
