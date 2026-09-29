# USE CASE: 7 Update an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to update an employee's details* so that *employee's details are kept up-to-date.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the employee ID. The employee exists in the database.

### Success End Condition

The employee's updated details are stored in the HR system.

### Failed End Condition

The employee's details are not updated.

### Primary Actor

HR Advisor.

### Trigger

A request to update an employee's details is made by HR.

## MAIN SUCCESS SCENARIO

1. HR advisor enters the employee ID.
2. HR system searches for the employee using the employee ID.
3. HR advisor enters the updated employee details.
4. HR system checks that the updated details are valid.
5. HR system updates the employee's details in the database.
6. HR system confirms that the employee's details have been updated.

## EXTENSIONS

2. **Employee does not exist**:
    1. HR system informs the HR advisor that the employee does not exist.
    2. No employee details are updated.

4. **Updated details are invalid**:
    1. HR system informs the HR advisor that the details are invalid.
    2. HR advisor corrects the employee details.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0