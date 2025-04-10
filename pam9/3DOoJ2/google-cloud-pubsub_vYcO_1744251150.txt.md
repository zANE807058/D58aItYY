# Contributing to Unifrost

Welcome! Our community focuses on helping others and making Unifrost the best it can be. We gladly
accept contributions and encourage you to get involved!

## Bug Reports

First, please [search this repository](https://github.com/unifrost/unifrost/search?q=&type=Issues&utf8=%E2%9C%93)
with a variety of keywords to ensure your bug is not already reported.

If not, [open an issue](https://github.com/unifrost/unifrost/issues) and answer the questions so we
can understand and reproduce the problematic behavior.

The burden is on you to convince us that it is actually a bug in Unifrost. This is easiest to do when
you write clear, concise instructions so we can reproduce the behavior (even if it seems obvious).
The more detailed and specific you are, the faster we will be able to help you. Check out [How to
Report Bugs Effectively](https://www.chiark.greenend.org.uk/~sgtatham/bugs.html).

Please be kind. :smile:

## Minor Improvements and New Tests

Submit [pull requests](https://github.com/unifrost/unifrost/pulls) at any time. Make sure to write
tests to assert your change is working properly and is thoroughly covered.

## New Features

First, please [search](https://github.com/unifrost/unifrost/search?q=&type=Issues&utf8=%E2%9C%93) with
a variety of keywords to ensure your suggestion/proposal is new.

Please also check for existing pull requests to see if someone is already working on this. We want
to avoid duplication of effort.

If the proposal is new and no one has opened pull request yet, you may open either an issue or a
pull request for discussion and feedback.

If you are going to spend significant time implementing code for a pull request, best to open an
issue first and "claim" it and get feedback before you invest a lot of time.

If possible make a pull request as small as possible, or submit multiple pull request to complete a
feature. Smaller means: easier to understand and review. This in turn means things can be merged
faster.

## Updating Dependencies

We use [Go Modules](https://github.com/golang/go/wiki/Modules) as the tool to manage vendor dependencies.

Use the following to update the version of all dependencies

```shell
$ go get -u
```

After the dependencies have been updated or added, you might run the following to
cleanup the go module files:

```shell
$ go mod tidy
```

Please refer to [Go Modules](https://github.com/golang/go/wiki/Modules) for more details.
