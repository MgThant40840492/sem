# USE CASE: 6 View an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to view an employee's details* so that *the employee's promotion request can be supported.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the employee ID. The employee exists in the database.

### Success End Condition

The employee's current details are displayed to the HR advisor.

### Failed End Condition

The employee's details cannot be displayed.

### Primary Actor

HR Advisor.

### Trigger

A request to view an employee's details is made by HR.

## MAIN SUCCESS SCENARIO

1. HR advisor enters the employee ID.
2. HR system searches for the employee using the employee ID.
3. HR system retrieves the employee's current details.
4. HR system displays the employee's details to the HR advisor.

## EXTENSIONS

2. **Employee does not exist**:
    1. HR system informs the HR advisor that the employee does not exist.
    2. No employee details are displayed.

3. **Employee details cannot be retrieved**:
    1. HR system informs the HR advisor that the employee details cannot be retrieved.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0