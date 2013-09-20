%global commit ef43ae7cb406f09a5cd573db9e992910ac544cf3
%global shortcommit %(c=%{commit}; echo ${c:0:7})

Name:           jabrt
Version:        1.0
Release:        1%{?dist}
Summary:        ABRT Java bindings

License:        Applications/System
URL:            https://github.com/mozeq/%{name}
#Source0:        https://github.com/mozeq/%{name}/archive/%{commit}/%{name}-%{version}-%{shortcommit}.tar.gz
Source0:        %{name}-%{version}-%{shortcommit}.tar.gz
BuildArch:      noarch

BuildRequires:  java-devel >= 1:1.6.0
BuildRequires:  maven-local
BuildRequires:  jnr-unixsocket >= 0.2
BuildRequires:  jpackage-utils

Requires:       java >= 1:1.6.0
Requires:       jpackage-utils

%description
ABRT Java bingings prividing a convenient way to report problems

%package javadoc
Summary: API documentation for %{name}

%description javadoc
This package contains %{summary}.

%prep
%setup -q -n %{name}-%{version}-%{commit}

%build
%mvn_build

%install
%mvn_install

%files -f .mfiles
%dir %{_javadir}/%{name}
%doc

%files javadoc -f .mfiles-javadoc
%doc

%changelog
* Fri Sep  6 2013 Jiri Moskovcak <jmoskovc@redhat.com> 1.0-1
- initial packaging