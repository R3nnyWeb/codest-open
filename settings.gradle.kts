rootProject.name = "codest"

include(":task-executor")
include(":task-microservice")
include(":task-shared-domain")
include("task-microservice:domain")
findProject(":task-microservice:domain")?.name = "domain"

