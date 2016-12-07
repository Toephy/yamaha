package com.lixing.concurrentunit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Toephy on 2016.10.13 16:49
 */
public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < 100; i++) {
            students.add(new Student("student" + i, new Random().nextDouble()));
        }

        ForkJoinPool pool = new ForkJoinPool();
        ComputeTask task = new ComputeTask(students);
        ForkJoinTask<Double> result = pool.submit(task);
        Double compute = result.get();
        System.out.println(compute);
    }


    static class ComputeTask extends RecursiveTask<Double> {

        private static final int THRESHOLD = 10;
        private List<Student> seeds;
        private double sum;

        public ComputeTask(List<Student> seeds) {
            this.seeds = seeds;
        }

        @Override
        protected Double compute() {
            if (seeds.size() < THRESHOLD) {
                for (Student s : seeds) {
                    sum += s.getScore();
                }
            } else {
                int middle = seeds.size() / 2;
                List<Student> s1 = seeds.subList(0, middle);
                List<Student> s2 = seeds.subList(middle, seeds.size());
                ComputeTask t1 = new ComputeTask(s1);
                ComputeTask t2 = new ComputeTask(s2);
                invokeAll(t1, t2);
                sum = t1.join() + t2.join();
            }
            return sum;
        }

        public List<Student> getSeeds() {
            return seeds;
        }

        public void setSeeds(List<Student> seeds) {
            this.seeds = seeds;
        }

    }

    static class Student {

        private String name;
        private double score;

        public Student(String name, double score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}
