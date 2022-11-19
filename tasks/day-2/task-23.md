# Portfolio & security

It's time to finally introduce some basic security to our project.

For now, let's secure only one new module of CrowdSorcery: the
`portfolio` module. The implementation of portfolio should be extremely
simple: it's just a CRUD-like system for investors, which allows them to
prepare the view highlighting their investments.

In portfolio, investors can:

1. Create new investment
2. Edit the investment
3. Delete the investment

Everyone, even not registered users, can:

1. View the public portfolio of any investor
2. List all public portfolios

Your task is to create the portfolio module and secure its REST APIs by using
Spring Security and Basic Authentication. Rules:

1. There's only one portfolio for each investor. The portfolio should be
   automatically created when investor registers.
2. Only owner of the portfolio can add, edit and delete investments from her
   portfolio.
3. Owner of the portfolio might decide that portfolio is no longer publicly
   available.