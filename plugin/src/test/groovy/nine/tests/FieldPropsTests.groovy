package nine.tests

import gorm.FieldProps
import org.grails.config.PropertySourcesConfig
import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class FieldPropsTests extends Specification implements GrailsUnitTest {


    void test_buildFiledPropsMap() {
        when:
        ConfigObject co = new ConfigSlurper().parse(testConfig);

        then:
        assert co.grails.plugin.audittrail
        assert FieldProps.getMap(co, "grails.plugin.audittrail.createdBy")
        assert !FieldProps.getMap(co, "grails.plugin.audittrail.xxx")
        //assert co.getProperty("grails.plugin.audittrail")
        def fmap = FieldProps.buildFieldMap(new PropertySourcesConfig(co))
        assert fmap
        assert fmap.get("createdBy")
        assert fmap.get("createdBy").name == "blah"
        assert fmap.get("createdBy").type == Long

        assert fmap.get("createdDate") == null
        //FieldProps.buildFieldMap()
    }

    def testConfig = """
        grails{
            plugin{
                audittrail{
                    //this should have all the defaults
                    createdBy.field   = "blah"

                    editedBy.type   = "java.lang.String"
                    editedBy.constraints = "nullable:true,bindable:false"

                    editedDate.type  = "java.util.Date"
                    editedDate.mapping = "type: org.jadira.usertype.dateandtime.joda.PersistentDateTime"

                    currentUserClosure = {ctx->
                        return "RON"
                    }
                }
            }
        }

    """
}
