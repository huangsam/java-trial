package io.huangsam.trial.stdlib.generics;

/**
 * Demonstrates the Fluent Builder pattern using recursive generics
 * (Self-Types).
 * This pattern ensures that subclasses return their own type for builder
 * methods
 * defined in a parent class, enabling smooth method chaining across an
 * inheritance hierarchy.
 */
public class FluentExplorer {

    /**
     * The base builder uses a recursive generic
     * {@code <B extends PersonBuilder<B>>}.
     * This forces implementations to return their specific builder type.
     */
    public abstract static class PersonBuilder<B extends PersonBuilder<B>> {
        protected String name;
        protected int age;

        /**
         * Returns 'this' cast to the explicit generic type.
         *
         * @return the correctly typed builder instance
         */
        protected abstract B self();

        /**
         * Sets the person's name.
         *
         * @param name the name to set
         * @return the current builder instance
         */
        public B withName(String name) {
            this.name = name;
            return self();
        }

        /**
         * Sets the person's age.
         *
         * @param age the age to set
         * @return the current builder instance
         */
        public B withAge(int age) {
            this.age = age;
            return self();
        }
    }

    /**
     * A concrete builder for a Student that extends the base PersonBuilder.
     */
    public static class StudentBuilder extends PersonBuilder<StudentBuilder> {
        private String major;

        @Override
        protected StudentBuilder self() {
            return this;
        }

        /**
         * Sets the student's major.
         *
         * @param major the major to set
         * @return the current builder instance
         */
        public StudentBuilder withMajor(String major) {
            this.major = major;
            return self();
        }

        /**
         * Builds the student record.
         *
         * @return a new Student instance
         */
        public Student build() {
            return new Student(name, age, major);
        }
    }

    /**
     * The target model class to be built.
     * Record type is a modern Java feature introduced in Java 14 (preview) / 16
     * (standard).
     */
    public record Student(String name, int age, String major) {
    }

}
