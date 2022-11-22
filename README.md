# Spring Training v2 tasks and examples

Welcome to the renewed and polished Spring Training repository!

If you ever tried to learn Spring and failed because of lots of outdated
details - this is the right place for you! I've prepared a curated selection
of tasks (currently **26** tasks) that will help you to learn the modern Spring Framework in practice.

This repository is a part of
my [Spring & Spring Boot Training](https://bottega.com.pl/training-spring-boot).

Try out my new training program [howtobe.pro](https://www.howtobe.pro/) and become
**strong**, proud, and **professional** software developer.

Check out my [other trainings](https://bottega.com.pl/trener-mike-wojtyna)!

## Guide

You should start with the description of the project, we're going to utilize in our tasks and examples - the [CrowdSorcery!](crowd-sorcery/crowd-sorcery.md).

### How to work with tasks

Each task is marked with at least two tags `task-x-start` and `task-x-end`. There can be also intermediate steps starting from `task-x-step-0`. So, for example, if you'd like to start your adventure with Spring from first task, you should invoke the following command:

`git checkout task-1-start`

The detailed description of all tasks can be found at `HEAD` of `master` branch in the [tasks directory](tasks).

If you're stuck - go ahead and try with next step, e.g. `git checkout task-2-step-0`.

```shell
$ git tag -n | fgrep task-2-
task-2-end      Implement REST investor profile client
task-2-start    Add task 2 description.
task-2-step-0   Extract CliAdapter
task-2-step-1   Change InvestorService to accept RegisterInvestor command
task-2-step-2   Extract CliCommandsMapper
task-2-step-3   Change investor to include profile and add investor profile service
task-2-step-4   Change cli commands mapper from simple name to surname aware mapper
```

## Building

Simply call `./mvnw package` to create an executable jar or `./mvnw spring-boot:run` to start the app.

## Let's stay in touch!

Mike Wojtyna

- [My training program](https://www.howtobe.pro/)
- [My personal website](http://wojtyna.pl)
- [Linkedin](https://www.linkedin.com/in/michalwojtyna/)
- [GitHub](https://github.com/mikewojtyna)