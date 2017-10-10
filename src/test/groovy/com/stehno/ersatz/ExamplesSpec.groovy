/*
 * Copyright (C) 2017 Christopher J. Stehno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stehno.ersatz

import spock.lang.Specification

/**
 * These tests are pulled from the various examples in the project documents.
 */
class ExamplesSpec extends Specification {

    def 'index.html example'() {
        setup:
        ErsatzServer ersatz = new ErsatzServer()

        ersatz.expectations {
            get('/say/hello') {
                called 1
                query 'name', 'Ersatz'
                responder {
                    content 'Hello Ersatz', 'text/plain'
                }
            }
        }

        when:
        URL url = "${ersatz.httpUrl}/say/hello?name=Ersatz".toURL()

        then:
        url.text == 'Hello Ersatz'
        ersatz.verify()

        cleanup:
        ersatz.stop()
    }

    def 'say hello'(){
        setup:
        ErsatzServer ersatz = new ErsatzServer()

        ersatz.expectations {
            get('/say/hello'){
                called 1
                query 'name','Ersatz'
                responder {
                    content 'Hello Ersatz','text/plain'
                }
            }
        }

        when:
        String result = "${ersatz.httpUrl}/say/hello?name=Ersatz".toURL().text

        then:
        result == 'Hello Ersatz'

        and:
        ersatz.verify()

        cleanup:
        ersatz.stop()
    }
}