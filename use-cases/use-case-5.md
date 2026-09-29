# USE CASE: 5 Add a New Employee

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to add a new employee's details* so that *I can ensure the new employee is paid.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The HR advisor has the new employee's details. The employee does not already exist in the database.

### Success End Condition

The new employee's details are stored in the HR system.

### Failed End Condition

The new employee's details are not added.

### Primary Actor

HR Advisor.

### Trigger

A new employee needs to be added to the HR system.

## MAIN SUCCESS SCENARIO

1. HR advisor enters the new employee's details.
2. HR system checks that the required employee details are valid.
3. HR system adds the new employee's details to the database.
4. HR system confirms that the employee has been added.

## EXTENSIONS

2. **Employee details are invalid**:
    1. HR system informs the HR advisor that the details are invalid.
    2. HR advisor corrects the employee details.

3. **Employee already exists**:
    1. HR system informs the HR advisor that the employee already exists.
    2. No new employee record is created.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0