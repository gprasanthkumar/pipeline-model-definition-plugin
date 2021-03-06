/*
 * The MIT License
 *
 * Copyright (c) 2017, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

pipeline {
    agent none
    stages {
        stage('Parallel') {
            parallel {
                stage('Child 1') {
                    agent any
                    steps { echo 'Child 1' }
                }
                stage('Child 2') {
                    agent any
                    stages {
                        stage('Nested 1') {
                            steps {
                                echo 'Nested 1'
                            }
                            post {
                                always {
                                    echo "Post Nested 1 ran"
                                }
                            }
                        }
                        stage('Nested 2') {
                            steps {
                                echo 'Nested 2'
                            }
                            post {
                                always {
                                    echo "Post Nested 2 ran"
                                }
                            }
                        }
                    }
                    post {
                        always {
                            echo "Post Child 2 ran"
                        }
                    }
                }
            }
            post {
                always {
                    echo 'Post ran'
                }
            }
        }
    }
}
