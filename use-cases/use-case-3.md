# USE CASE: 3 Produce a Report on the Salary of Employees in My Department

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *department manager* I want *to produce a report on the salary of employees in my department* so that *I can support financial reporting for my department.*

### Scope

Department.

### Level

Primary task.

### Preconditions

The department manager is assigned to a department. Database contains current employee salary data.

### Success End Condition

A report is available for the department manager to use for financial reporting.

### Failed End Condition

No report is produced.

### Primary Actor

Department Manager.

### Trigger

A request for financial information is made by the department manager.

## MAIN SUCCESS SCENARIO

1. Department manager requests salary information for their department.
2. HR system identifies the department of the manager.
3. HR system extracts current salary information of all employees in the manager's department.
4. HR system provides the report to the department manager.

## EXTENSIONS

2. **Manager is not assigned to a department**:
    1. System informs the manager that no department is assigned to them.

3. **No employees exist in the department**:
    1. System informs the manager that no employee salary information is available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0