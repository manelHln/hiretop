import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import axios from "axios";

const handler = NextAuth({
  providers: [
    CredentialsProvider({
      async authorize(credentials, req) {
        return axios
          .post(`${process.env.NEXT_PUBLIC_BASE_API_URL}auth/login/${credentials.userType}`, {
            email: credentials.email,
            password: credentials.password,
          })
          .then((result) => {
            const user = result.data.content;
            return {
              name: user.name,
              email: user.email,
              accessToken: user.token,
              role: user.role,
              image: user.image
            };
          })
          .catch((error) => {
            throw new Error(error.response?.data?.error?.message || error.message);
          });
      },
    }),
  ],
  pages: {
    signIn: "/login",
  },
  session: {
    strategy: "jwt",
    maxAge: 10 * 24 * 60 * 60, // 10 days
  },
  callbacks: {
    async jwt({ token, user }) {
      if (user) {
        token.email = user.email;
        token.name = user.name;
        token.role = user.role;
        token.picture = user.image
        token.accessToken = user.accessToken;
      }
      return token;
    },
    async session({ session, token }) {
      session.user = {
        name: token.name,
        email: token.email,
        role: token.role,
        image: token.picture
      };
      session.accessToken = token.accessToken;
      return session;
    },
  },
});

export { handler as GET, handler as POST };
