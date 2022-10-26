# Implementation of Sorting, Pagination using Spring Data JPA

# Sorting with Spring JPA

## Intro

1. Spring Data JPA supports sorting to Query results with easier configurations.
2. Spring Data JPA provides default implementations of sorting with the help of ``PagingAndSortingRepository``
   interface.
3. There are two ways to achieve sorting in the Spring Data JPA:
    1. Static Sorting
    2. Dynamic Sorting

## Static Sorting

1. Static sorting refers to the mechanism where the retrieved data is always sorted by specified columns and directions.
   The column and sort directions are defined at the development time and can not be changed at the runtime.
2. Below is an example of static sorting.

````java
List<Person> FindByOrderByNameDes();
````

## Dynamic Sorting

1. By using dynamic sorting, you can choose the sorting column and direction at runtime to sort the query results.
2. Dynamic Sorting provides more flexibility in choosing sort columns and directions with the help of ``Sort`` parameter
   to your query method. The sort class is just a specification that provides sorting options for database queries.
   Below is an example.
````java
Sort sort = Sort.by("Name").decending().and(Sort.by("age"));
```` 
