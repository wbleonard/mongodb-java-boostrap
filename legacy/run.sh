# Options include "URI" (default) or "ServerAddress" (old style)
# export mongodb_connection_style="ServerAddress"

# Set if using URI Connection Style
export mongodb_uri="mongodb://localhost:27017"

# Set if using ServerAddress Connection Style
export server="<Primary Node>"
export port=27017
export username="<username>"
export password="<password>"

java -cp target/java-bootstrap-1.0-SNAPSHOT-jar-with-dependencies.jar com.mongodb.java.bootstrap.App




