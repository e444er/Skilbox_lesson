package com.devv.navviewmodel

import kotlin.random.Random

class PersonPepository {

        var persons = listOf(
            Person.Developer(
                id = 1,
                name = "Иван Петров",
                avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
                age = 34,
                programLong = "java"
            ),
            Person.User(
                id = 2,
                name = "Саша Иванов",
                avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
                age = 19
            ),
            Person.Developer(
                id = 3,
                name = "Тор Молот",
                avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
                age = 26,
                programLong = "kotlin"
            ),
            Person.User(
                id = 4,
                name = "Халк зеленый",
                avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
                age = 44,
            ),
            Person.Developer(
                id = 5,
                name = "Вдова Черный",
                avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
                age = 25,
                programLong = "python"
            ),
            Person.User(
                id = 6,
                name = "Пантера Черный",
                avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
                age = 27,
            ),
            Person.Developer(
                id = 7,
                name = "Танос Химия",
                avatarLink = "https://cdn.pixabay.com/photo/2013/10/22/07/56/android-199225_960_720.jpg",
                age = 23,
                programLong = "C++"
            ),
        )


        fun createPerson(): Person {
            persons.random().let {
                return when (it) {
                    is Person.Developer -> it.copy(id = Random.nextLong())
                    is Person.User -> it.copy(id = Random.nextLong())
                }
            }

        }

        fun deletePerson(persons: List<Person>, position: Int): List<Person> {
            return persons.filterIndexed { index, user -> index != position }
        }

    }
