# Jackson CSV Bug with empty files
When a schema is configured with a header, and the reader is configured to fail on missing columns,
a header that does not match the type we want to match to, results in an empty list, rather than an exception
being thrown