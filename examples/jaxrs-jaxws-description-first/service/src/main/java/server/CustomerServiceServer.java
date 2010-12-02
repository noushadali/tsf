/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package server;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;

import com.example.customerservice.CustomerService;

public class CustomerServiceServer {

    protected CustomerServiceServer() throws Exception {
        System.out.println("Starting Server");
        CustomerService implementor = new CustomerServiceImpl();
        Endpoint.publish("http://localhost:8080/services/soap",
                                                         implementor);

        JAXRSServerFactoryBean jaxrsFactory = new JAXRSServerFactoryBean(); 
        jaxrsFactory.setAddress("http://localhost:8080/services/rest");
        jaxrsFactory.setModelRef("classpath:/CustomerService-jaxrs.xml");
        jaxrsFactory.setServiceBean(implementor);
        
        JAXBElementProvider provider = new JAXBElementProvider();
        provider.setMarshallAsJaxbElement(true);
        jaxrsFactory.setProvider(provider);
        
        jaxrsFactory.create();
    }

    public static void main(String args[]) throws Exception {
        new CustomerServiceServer();
        System.out.println("Server ready...");
        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
