# More services!

`git checkout task-2-start`

Let's change `InvestorService` to accept the `RegisterInvestor` command
instead of `Investor`,
so the
intention is clear. The `RegisterInvestor` command should include only `id` and
`name`.
Now, extract the logic out of main class into new
`CliInvestorAdapter` class. Allow `CliInvestorAdapter` to be configured with
any argument mapper. For example:

```java
import java.util.Optional;

class CliAdapter {

    private CliCommandsMapper commandsMapper;
    // TODO: all the other fields

    void run(String[] args) {
        throw new UnsupportedOperationException("run the main logic here");
    }
}

interface CliCommandsMapper {

    Optional<RegisterInvestor> map(String[] args);
}
```

Provide at least two implementations of `CliCommandsMapper`. Details are not
important at this moment, let's call them e.g. `ACliCommandsMapper` and
`BCliCommandsMapper`. You can
even use the same code underneath.

Let's imagine some requirements changed, and now we need to save
enhanced Investors in the database. Investor should now include also the
full profile, which is available via `InvestorProfileService`.

That's how the `InvestorProfile` record might look like.

```java
record InvestorProfile(int score, boolean isVip, String referralLink) {

}
```

Now, create two implementations of `InvestorProfileService`: the first one
using some local repository `LocalInvestorProfileRepository` (you can use the
in-memory implementation), and the second
one calling some external REST API and using
the [Unirest library](https://github.com/Kong/unirest-java).
For your convenience, the implementation of the REST API server is provided
in the `external` package. Simply execute the server before running the main
application.

First, let's run the application configured with
`LocalInvestorProfileRepository` and `ACliCommandsMapper`. After successfully
executing
the application, change the configuration to use REST API investor profile
service and `BCliCommandsMapper`.

Was that hard? Now, imagine you need to configure and instantiate hundreds
of such services.