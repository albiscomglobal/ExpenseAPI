FROM openjdk:17

COPY target/ExpenseTrackerAPI.jar ExpenseTrackerAPI.jar

ENTRYPOINT ["java", "-jar", "/ExpenseTrackerAPI.jar"]