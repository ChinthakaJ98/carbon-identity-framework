/*
 * Copyright (c) 2023, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.application.authentication.framework.util.auth.service;

import java.util.Optional;

/**
 * Constants class for Auth Service.
 */
public class AuthServiceConstants {

    /**
     * Enum for flow status.
     */
    public enum FlowStatus {

        SUCCESS_COMPLETED,
        FAIL_COMPLETED,
        FAIL_INCOMPLETE,
        INCOMPLETE
    }

    public static final String AUTH_SERVICE_AUTH_INITIATION_DATA = "authServiceAuthInitiationData";
    public static final String AUTHENTICATORS = "authenticators";
    public static final String FLOW_ID = "flowId";
    public static final String AUTHENTICATOR_SEPARATOR = ";";
    public static final String AUTHENTICATOR_IDP_SEPARATOR = ":";
    public static final String INTERNAL_ERROR_MSG_SEPARATOR = "-";
    public static final String AUTH_FAILURE_PARAM = "authFailure";
    public static final String AUTH_FAILURE_MSG_PARAM = "authFailureMsg";
    public static final String ERROR_CODE_PARAM = "errorCode";
    public static final String ERROR_CODE_PREFIX = "ABA-";

    /**
     * Enum for error messages.
     */
    public enum ErrorMessage {

        // Client errors starting from 600xx.
        /* The 60001 ERROR_INVALID_AUTH_REQUEST is used as the default client error
         therefor be cautious if that is being changed.*/
        ERROR_INVALID_AUTH_REQUEST("60001",
                "Invalid authentication request.",
                "Received authentication request is invalid."),
        ERROR_AUTHENTICATION_FAILURE("60002",
                "Authentication failure.",
                "Authentication flow has concluded with a failure."),
        ERROR_AUTHENTICATION_FAILURE_RETRY_AVAILABLE("60003",
                "Authentication failure.",
                "Authentication failure please retry."),
        ERROR_INVALID_AUTHENTICATOR_ID("60004",
                "Invalid authenticatorId.",
                "Provided authenticatorId %s is invalid."),

        // Server Error starting from 650xx.
        /* The 65001 ERROR_UNABLE_TO_PROCEED is used as the default server error
         therefor be cautious if that is being changed.*/
        ERROR_UNABLE_TO_PROCEED("65001",
                "Unable to proceed with authentication.",
                "Server encountered an error while processing the authentication request."),
        ERROR_AUTHENTICATOR_NOT_FOUND("65002",
                "Unable to find authenticator.",
                "Authenticator not found for name: %s"),
        ERROR_UNKNOWN_AUTH_FLOW_STATUS("65003",
                "Unknown authentication flow status.",
                "Unknown authentication flow status: %s");
        private final String code;
        private final String message;
        private final String description;

        ErrorMessage(String code, String message, String description) {

            this.code = code;
            this.message = message;
            this.description = description;
        }

        public String code() {

            return ERROR_CODE_PREFIX + code;
        }

        public String message() {

            return message;
        }

        public String description() {

            return description;
        }

        /**
         * Get error message enum from code.
         * @param code error code.
         * @return error message enum.
         */
        public static Optional<ErrorMessage> fromCode(String code) {

            for (ErrorMessage error : ErrorMessage.values()) {
                if (error.code.equals(code)) {
                    return Optional.of(error);
                }
            }
            return Optional.empty();
        }

        @Override
        public String toString() {

            return code + " | " + message;
        }
    }
}
