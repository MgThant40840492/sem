# USE CASE: 8 Delete an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to delete an employee's details* so that *the company is compliant with data retention legislation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the employee ID. The employee exists in the database.

### Success End Condition

The employee's details are deleted from the HR system.

### Failed End Condition

The employee's details are not deleted.

### Primary Actor

HR Advisor.

### Trigger

A request to delete an employee's details is made by HR.

## MAIN SUCCESS SCENARIO

1. HR advisor enters the employee ID.
2. HR system searches for the employee using the employee ID.
3. HR system displays the employee's details for confirmation.
4. HR advisor confirms that the employee's details should be deleted.
5. HR system deletes the employee's details from the database.
6. HR system confirms that the employee's details have been deleted.

## EXTENSIONS

2. **Employee does not exist**:
    1. HR system informs the HR advisor that the employee does not exist.
    2. No employee details are deleted.

4. **HR advisor does not confirm deletion**:
    1. HR system cancels the deletion.
    2. The employee's details remain in the database.

5. **Employee details cannot be deleted**:
    1. HR system informs the HR advisor that the employee's details could not be deleted.
    2. The employee's details remain in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0