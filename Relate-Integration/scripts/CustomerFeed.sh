#!/bin/bash

echo "Began execution of Command"

SHELL_CP=$RELATE_MODULE_APP_ROOT\WEB-INF\conf:$RELATE_MODULE_APP_ROOT\WEB-INF\classes:$RELATE_MODULE_APP_ROOT\WEB-INF\lib\*:

java -cp $SHELL_CP com.rim.integration.ImpexExecutor import customer $RELATE_MODULE_CONTEXT_PATH

echo "Finished execution of Command"