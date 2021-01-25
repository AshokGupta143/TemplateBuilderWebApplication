Add the following below lines, search for '  set "JAVA_OPTS=%JAVA_OPTS% ' and add after this serach



rem  =============***** SEt the Authentication for CouchDB configuration*****************================
set "JAVA_OPTS=%JAVA_OPTS% -Djava.security.auth.login.config==%CATALINA_BASE%\conf\jaas.config"
