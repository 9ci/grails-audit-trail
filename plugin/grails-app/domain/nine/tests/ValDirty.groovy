/*
* Copyright 2019 Yak.Works - Licensed under the Apache License, Version 2.0 (the "License")
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
*/
package nine.tests

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ValDirty {

    String name

    @SuppressWarnings(['Println'])
    def beforeValidate(List blah) {
        println 'ValDirty beforeValidate'
    }
}
